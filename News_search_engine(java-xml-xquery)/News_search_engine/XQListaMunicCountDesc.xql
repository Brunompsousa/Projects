xquery version "1.0";

<Resultado>{

for $autor in doc("pesqListaMunic.xml")//Munic
order by $autor/Num descending
return 
	<Munic>
	<nome> {data($autor/nome)}</nome>
	<Num> {data($autor/Num)}</Num>
	</Munic>

}</Resultado>