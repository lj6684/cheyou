# encoding: utf-8

require "win32ole"
require "iconv"

def format(str)
  index = str.index('.')  
  if index != nil and index > 0
    str[0, index]
  else
    str
  end
end

excel = WIN32OLE.new('excel.application')
excel.Visible = true
workBook = excel.WorkBooks.Open('c:/filter.xlsx')
workSheet = workBook.Worksheets(1)
workSheet.Select

brandId = 0
brandArray = []

line = 2
brand = ''
conv = Iconv.new("utf-8", "GBK")
file = File.open('./insert_filter.sql', 'w')
file.puts "INSERT INTO supply(supply_id, supply_name) VALUES(1, '博世');"

while workSheet.Range("b#{line}").Value
  brand = conv.iconv(workSheet.Range("a#{line}").Value) if workSheet.Range("a#{line}").Value
  if not brandArray.include?(brand)
    brandId += 1
    brandArray.push(brand)
    file.puts "INSERT INTO brand(brand_id, brand_name) VALUES(#{brandId}, '#{brand}');"
  end
  style = conv.iconv(workSheet.Range("b#{line}").Value)
  air = format(workSheet.Range("c#{line}").Value.to_s)
  machine_oil = format(workSheet.Range("d#{line}").Value.to_s)
  fuel_oil = format(workSheet.Range("e#{line}").Value.to_s)
  air_condition_std = format(workSheet.Range("f#{line}").Value.to_s)
  air_condition_carbon = format(workSheet.Range("g#{line}").Value.to_s)

  file.puts "INSERT INTO style(style_id, style_name, brand_id) VALUES(#{line}, '#{style}', #{brandId});"
  file.puts "INSERT INTO filter(supply_id, brand_id, style_id, air, machine_oil, fuel_oil, air_condition_std, air_condition_carbon) VALUES (1, #{brandId}, #{line}, '#{air}', '#{machine_oil}', '#{fuel_oil}', '#{air_condition_std}', '#{air_condition_carbon}');"
  
  line += 1
end

file.close

excel.ActiveWorkBook.Close(1)
excel.Quit