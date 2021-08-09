xquery version "1.0";

<Resultado>{

let $municipios := doc("pesqListaMunic.xml")//nome
	for $autor in distinct-values(doc("pesqListaMunic.xml")//nome)
	return 
		<Munic>
		<nome> {data($autor)}</nome>
		<Num> {count($municipios[. eq $autor])}</Num>
		</Munic>

}</Resultado>