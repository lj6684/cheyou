# encoding: utf-8

require "win32ole"
require "iconv"

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
  air = workSheet.Range("c#{line}").Value.to_s[0..9]
  machine_oil = workSheet.Range("d#{line}").Value.to_s[0..9]
  fuel_oil = workSheet.Range("e#{line}").Value.to_s[0..9]
  air_condition_std = workSheet.Range("f#{line}").Value.to_s[0..9]
  air_condition_carbon = workSheet.Range("g#{line}").Value.to_s[0..9]

  file.puts "INSERT INTO filter(supply, brand, type, air, machine_oil, fuel_oil, air_condition_std, air_condition_carbon) VALUES ('#{supply}', '#{brand}', '#{type}', '#{air}', '#{machine_oil}', '#{fuel_oil}', '#{air_condition_std}', '#{air_condition_carbon}');"
  
  line += 1
end

file.close

excel.ActiveWorkBook.Close(1)
excel.Quit