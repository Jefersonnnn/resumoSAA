# resumoSSA

Sub Calculo_VD()
'
' Calculo_VD Macro
'

'

    Columns("D:D").ColumnWidth = 11.57
    Range("D6").Select
    ActiveCell.FormulaR1C1 = "Volumes"
    Range("E6").Select
    ActiveCell.FormulaR1C1 = "Dia"
    Range("D7").Select
    ActiveCell.FormulaR1C1 = _
        "=IF(R[1]C[-1]="""","""",((RC[-3]+R[1]C[-3])/2*((R[1]C[-1]-RC[-1])*86400))/1000)"
    Range("E7").Select
    ActiveCell.FormulaR1C1 = "=INT(RC[-2])"
    Range("D7:E7").Select
    Selection.AutoFill Destination:=Range("D7:E65536"), Type:=xlFillDefault
    Range("D7:E65536").Select
    Range("D65536").Select
    Selection.ClearContents
    Selection.End(xlUp).Select
    Selection.End(xlUp).Select
    ActiveWindow.SmallScroll Down:=-30
    Columns("E:E").EntireColumn.AutoFit
    Columns("E:E").ColumnWidth = 19.57
    Range("G6").Select
    ActiveCell.FormulaR1C1 = "Valores Diários"
    Range("H6").Select
    Columns("G:G").EntireColumn.AutoFit
    ActiveCell.FormulaR1C1 = "Volume Distribuido"
    Range("H7").Select
    Columns("H:H").EntireColumn.AutoFit
    Range("I6").Select
    ActiveCell.FormulaR1C1 = "Mínima Noturna"
    Range("I7").Select
    Columns("I:I").EntireColumn.AutoFit
    Range("J6").Select
    ActiveCell.FormulaR1C1 = "Fator de Pesquisa"
    Range("J7").Select
    Columns("J:J").EntireColumn.AutoFit
    Columns("G:G").ColumnWidth = 21.71
    Range("G7").Select
    ActiveCell.FormulaR1C1 = "=RC[-2]"
    Range("G8").Select
    ActiveCell.FormulaR1C1 = "=R[-1]C+R5C7"
    Range("G5").Select
    ActiveCell.FormulaR1C1 = "1"
    Range("G8").Select
    Selection.AutoFill Destination:=Range("G8:G37"), Type:=xlFillDefault
    Range("G8:G37").Select
    ActiveWindow.SmallScroll Down:=-33
    Range("H7").Select
    ActiveCell.FormulaR1C1 = "=IF(SUMIF(R7C5:R65536C5,RC7,R7C4:R65536C4)<=0,"""",SUMIF(R7C5:R65536C5,RC7,R7C4:R65536C4))"
    Range("I7").Select
    Selection.FormulaArray = "=MIN(IF(R7C5:R65536C5=RC[-2],R7C1:R65536C1))"
    Range("J7").Select
    Selection.FormulaArray = _
        "=MIN(IF(R7C5:R65536C5=RC[-3],R7C1:R65536C1))/AVERAGE(IF(R7C5:R65536C5,RC[-3],R7C1:R65536C1))"
    Selection.FormulaArray = _
        "=MIN(IF(R7C5:R65536C5=RC[-3],R7C1:R65536C1))/AVERAGE(IF(R7C5:R65536C5=RC[-3],R7C1:R65536C1))"
    Selection.Style = "Percent"
    Selection.NumberFormat = "0.0%"
    Selection.NumberFormat = "0.00%"
    Range("H7:J7").Select
    Selection.NumberFormat = "0.000000"
    Selection.NumberFormat = "0.00000"
    Selection.NumberFormat = "0.0000"
    Selection.NumberFormat = "0.000"
    Selection.NumberFormat = "0.00"
    Selection.AutoFill Destination:=Range("H7:J37")
    Range("H7:J37").Select
    Range("H7").Select
End Sub




//FP e MN
Sub executa()
Application.ScreenUpdating = False



' executa Macro
'
' Atalho do teclado: Ctrl+q
'

Dim W           As Worksheet
Dim variavel    As Variant
Dim ultcel      As Range
Dim cont        As Long

Set W = Sheets("relatorio_medida_ponto_excel")
variavel = 0
cont = 0

'INICIA DATAS
W.Select
W.Range("D5").Select
ActiveCell.Formula = "=INT(C7)"
W.Range("D6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("E5").Select
ActiveCell.Formula = "=D5+1"
W.Range("E6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("F5").Select
ActiveCell.Formula = "=E5+1"
W.Range("F6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("G5").Select
ActiveCell.Formula = "=F5+1"
W.Range("G6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("H5").Select
ActiveCell.Formula = "=G5+1"
W.Range("H6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("I5").Select
ActiveCell.Formula = "=H5+1"
W.Range("I6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("J5").Select
ActiveCell.Formula = "=I5+1"
W.Range("J6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("K5").Select
ActiveCell.Formula = "=J5+1"
W.Range("K6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("L5").Select
ActiveCell.Formula = "=K5+1"
W.Range("L6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("M5").Select
ActiveCell.Formula = "=L5+1"
W.Range("M6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("N5").Select
ActiveCell.Formula = "=M5+1"
W.Range("N6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("O5").Select
ActiveCell.Formula = "=N5+1"
W.Range("O6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("P5").Select
ActiveCell.Formula = "=O5+1"
W.Range("P6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("Q5").Select
ActiveCell.Formula = "=P5+1"
W.Range("Q6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("R5").Select
ActiveCell.Formula = "=Q5+1"
W.Range("R6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("S5").Select
ActiveCell.Formula = "=R5+1"
W.Range("S6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("T5").Select
ActiveCell.Formula = "=S5+1"
W.Range("T6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("U5").Select
ActiveCell.Formula = "=T5+1"
W.Range("U6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("V5").Select
ActiveCell.Formula = "=U5+1"
W.Range("V6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("W5").Select
ActiveCell.Formula = "=V5+1"
W.Range("W6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("X5").Select
ActiveCell.Formula = "=W5+1"
W.Range("X6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("Y5").Select
ActiveCell.Formula = "=X5+1"
W.Range("Y6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("Z5").Select
ActiveCell.Formula = "=Y5+1"
W.Range("Z6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("AA5").Select
ActiveCell.Formula = "=Z5+1"
W.Range("AA6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("AB5").Select
ActiveCell.Formula = "=AA5+1"
W.Range("AB6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("AC5").Select
ActiveCell.Formula = "=AB5+1"
W.Range("AC6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("AD5").Select
ActiveCell.Formula = "=AC5+1"
W.Range("AD6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("AE5").Select
ActiveCell.Formula = "=AD5+1"
W.Range("AE6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("AF5").Select
ActiveCell.Formula = "=AE5+1"
W.Range("AF6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("AG5").Select
ActiveCell.Formula = "=AF5+1"
W.Range("AG6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
W.Range("AH5").Select
ActiveCell.Formula = "=AG5+1"
W.Range("AH6").Select
ActiveCell.FormulaR1C1 = "=R[-1]C+0.209"
Range("D5:AH6").Select
Selection.NumberFormat = "dd/mm/yyyy hh:mm:ss"
Columns("D:AH").EntireColumn.AutoFit
W.Range("D7").Select

Range(Selection, Selection.End(xlDown)).Select
Range(Selection, Selection.End(xlToRight)).Select
Selection.NumberFormat = "General"
    

Range("C7").Select
Do While ActiveCell.Value <> ""
If ActiveCell.Value >= Range("D5") Then
If ActiveCell.Value < Range("D6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("D1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("E5") Then
If ActiveCell.Value < Range("E6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("E1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("F5") Then
If ActiveCell.Value < Range("F6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("F1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1


End If
End If

If ActiveCell.Value >= Range("G5") Then
If ActiveCell.Value < Range("G6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("G1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("H5") Then
If ActiveCell.Value < Range("H6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("H1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("I5") Then
If ActiveCell.Value < Range("I6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("I1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("J5") Then
If ActiveCell.Value < Range("J6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("J1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("K5") Then
If ActiveCell.Value < Range("K6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("K1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("L5") Then
If ActiveCell.Value < Range("L6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("L1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("M5") Then
If ActiveCell.Value < Range("M6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("M1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("N5") Then
If ActiveCell.Value < Range("N6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("N1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("O5") Then
If ActiveCell.Value < Range("O6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("O1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("P5") Then
If ActiveCell.Value < Range("P6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("P1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("Q5") Then
If ActiveCell.Value < Range("Q6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("Q1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("R5") Then
If ActiveCell.Value < Range("R6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("R1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("S5") Then
If ActiveCell.Value < Range("S6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("S1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("T5") Then
If ActiveCell.Value < Range("T6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("T1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("U5") Then
If ActiveCell.Value < Range("U6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("U1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("V5") Then
If ActiveCell.Value < Range("V6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("V1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("W5") Then
If ActiveCell.Value < Range("W6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("W1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("X5") Then
If ActiveCell.Value < Range("X6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("X1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("Y5") Then
If ActiveCell.Value < Range("Y6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("Y1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("Z5") Then
If ActiveCell.Value < Range("Z6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("Z1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("AA5") Then
If ActiveCell.Value < Range("AA6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("AA1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("AB5") Then
If ActiveCell.Value < Range("AB6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("AB1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("AC5") Then
If ActiveCell.Value < Range("AC6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("AC1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("AD5") Then
If ActiveCell.Value < Range("AD6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("AD1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("AE5") Then
If ActiveCell.Value < Range("AE6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("AE1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("AF5") Then
If ActiveCell.Value < Range("AF6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("AF1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("AG5") Then
If ActiveCell.Value < Range("AG6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("AG1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value >= Range("AH5") Then
If ActiveCell.Value < Range("AH6") Then
variavel = ActiveCell.Offset(0, -2).Value
Set ultcel = W.Range("AH1500").End(xlUp).Offset(1, 0)
ultcel.Select
ActiveCell.Value = variavel
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("D6") Then
If ActiveCell.Value < Range("E5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("E6") Then
If ActiveCell.Value < Range("F5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("F6") Then
If ActiveCell.Value < Range("G5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("G6") Then
If ActiveCell.Value < Range("H5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("H6") Then
If ActiveCell.Value < Range("I5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("I6") Then
If ActiveCell.Value < Range("J5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("J6") Then
If ActiveCell.Value < Range("K5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("K6") Then
If ActiveCell.Value < Range("L5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("L6") Then
If ActiveCell.Value < Range("M5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("M6") Then
If ActiveCell.Value < Range("N5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("N6") Then
If ActiveCell.Value < Range("O5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("O6") Then
If ActiveCell.Value < Range("P5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("P6") Then
If ActiveCell.Value < Range("Q5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("Q6") Then
If ActiveCell.Value < Range("R5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("R6") Then
If ActiveCell.Value < Range("S5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("S6") Then
If ActiveCell.Value < Range("T5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("T6") Then
If ActiveCell.Value < Range("U5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("U6") Then
If ActiveCell.Value < Range("V5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("V6") Then
If ActiveCell.Value < Range("W5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("W6") Then
If ActiveCell.Value < Range("X5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("X6") Then
If ActiveCell.Value < Range("Y5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("Y6") Then
If ActiveCell.Value < Range("Z5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("Z6") Then
If ActiveCell.Value < Range("AA5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("AA6") Then
If ActiveCell.Value < Range("AB5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("AB6") Then
If ActiveCell.Value < Range("AC5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("AC6") Then
If ActiveCell.Value < Range("AD5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("AD6") Then
If ActiveCell.Value < Range("AE5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("AE6") Then
If ActiveCell.Value < Range("AF5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("AF6") Then
If ActiveCell.Value < Range("AG5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("AG6") Then
If ActiveCell.Value < Range("AH5") Then
Range("C7").Select
cont = cont + 1

End If
End If

If ActiveCell.Value > Range("AH6") Then
Range("C7").Select
cont = cont + 1
End If

'FINAL
W.Range("C7").Select
ActiveCell.Offset(cont, 0).Select

Loop

Range("D4:AH4").Select
Selection.NumberFormat = "General"
W.Range("D4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([D7:D1500])
W.Range("E4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([E7:E1500])
W.Range("F4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([F7:F1500])
W.Range("G4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([G7:G1500])
W.Range("H4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([H7:H1500])
W.Range("I4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([I7:I1500])
W.Range("J4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([J7:J1500])
W.Range("K4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([K7:K1500])
W.Range("L4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([L7:L1500])
W.Range("M4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([M7:M1500])
W.Range("N4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([N7:N1500])
W.Range("O4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([O7:O1500])
W.Range("P4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([P7:P1500])
W.Range("Q4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([Q7:Q1500])
W.Range("R4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([R7:R1500])
W.Range("S4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([S7:S1500])
W.Range("T4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([T7:T1500])
W.Range("U4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([U7:U1500])
W.Range("V4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([V7:V1500])
W.Range("W4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([W7:W1500])
W.Range("X4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([X7:X1500])
W.Range("Y4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([Y7:Y1500])
W.Range("Z4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([Z7:Z1500])
W.Range("AA4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([AA7:AA1500])
W.Range("AB4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([AB7:AB1500])
W.Range("AC4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([AC7:AC1500])
W.Range("AD4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([AD7:AD1500])
W.Range("AE4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([AE7:AE1500])
W.Range("AF4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([AF7:AF1500])
W.Range("AG4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([AG7:AG1500])
W.Range("AH4").Select
ActiveCell.Formula = Application.WorksheetFunction.Min([AH7:AH1500])

Range("D3").Select
    ActiveCell.FormulaR1C1 = "=AVERAGEIFS(R7C1:R50000C1,R7C3:R50000C3,"">=""&R[2]C,R7C3:R50000C3,""<""&R[2]C[1])"
    Range("D3").Select
    Selection.AutoFill Destination:=Range("D3:AG3"), Type:=xlFillDefault
W.Range("AH3").Select
ActiveCell.FormulaR1C1 = "=AVERAGEIFS(R7C1:R50000C1,R7C3:R50000C3,"">=""&R[2]C,R7C3:R50000C3,""<""&R[2]C[0]+0.999)"

Range("D2").Select
    ActiveCell.FormulaR1C1 = "=R[2]C/R[1]C"
    Range("D2").Select
    Selection.AutoFill Destination:=Range("D2:AH2"), Type:=xlFillDefault


Columns("D:D").Select
    Selection.Insert Shift:=xlToRight, CopyOrigin:=xlFormatFromLeftOrAbove
    Selection.ColumnWidth = 17.43
    Range("D4").Select
    ActiveCell.FormulaR1C1 = "Mínima"
    Range("D3").Select
    ActiveCell.FormulaR1C1 = "Média"
    Range("D2").Select
    ActiveCell.FormulaR1C1 = "FP"
  
Range("D2:AI4").Select
    Selection.NumberFormat = "0.00"
'FORMATA TAMANHO DA FONTE E ALINHAMENTO

Range("D2:D4").Select
    With Selection.Font
        .Name = "SansSerif"
        .Size = 10
        .Strikethrough = False
        .Superscript = False
        .Subscript = False
        .OutlineFont = False
        .Shadow = False
        .Underline = xlUnderlineStyleNone
        .ColorIndex = 1
        .TintAndShade = 0
        .ThemeFont = xlThemeFontNone
    End With
    With Selection
        .HorizontalAlignment = xlCenter
        .VerticalAlignment = xlCenter
        .WrapText = True
        .Orientation = 0
        .AddIndent = False
        .IndentLevel = 0
        .ShrinkToFit = False
        .ReadingOrder = xlContext
        .MergeCells = False
    End With
  
Application.ScreenUpdating = True

Range("A1").Select
  
End Sub
