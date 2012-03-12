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

line = 2
brand = ''

file = File.new('./insert_filter.sql', 'w')
conv = Iconv.new("utf-8", "GBK")

while workSheet.Range("b#{line}").Value
  supply = '博世'
  brand = conv.iconv(workSheet.Range("a#{line}").Value) if workSheet.Range("a#{line}").Value
  type = conv.iconv(workSheet.Range("b#{line}").Value)
  air = format(workSheet.Range("c#{line}").Value.to_s)
  machine_oil = format(workSheet.Range("d#{line}").Value.to_s)
  fuel_oil = format(workSheet.Range("e#{line}").Value.to_s)
  air_condition_std = format(workSheet.Range("f#{line}").Value.to_s)
  air_condition_carbon = format(workSheet.Range("g#{line}").Value.to_s)

  file.puts "INSERT INTO filter(supply, brand, type, air, machine_oil, fuel_oil, air_condition_std, air_condition_carbon) VALUES ('#{supply}', '#{brand}', '#{type}', '#{air}', '#{machine_oil}', '#{fuel_oil}', '#{air_condition_std}', '#{air_condition_carbon}');"
  
  line += 1
end

file.close

excel.ActiveWorkBook.Close(1)
excel.Quit