// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { INICIANTE, BASICO, INTERMEDIARIO, AVANÇADO, EXPECIALISTA }
enum class Stack { KOTLIN, JAVA, CSHARP}

data class Usuario (val nome: String, val interesse: Stack, var pontuacao: Int = 0)
fun Usuario.addPonto(pontos: Int): Unit {this.pontuacao+=pontos}

data class ConteudoEducacional(val nome: String, val duracao: Int, val stack: Stack, val pontos: Int = 1)

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>, val nivel: Nivel) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(vararg usuarios: Usuario) {
        println("\nInscrevendo alunos na formação de ${this.nome}:")
        for (u in usuarios){
            this.inscritos.add(u)
            println("\tUsuario ${u.nome} inscrito(a).")
        }   
    }
    
    fun formarUsuario(u: Usuario){
        u.addPonto(this.calcularPontos())
        inscritos.remove(u)
        println("\tUsuario(a) ${u.nome} formado em ${this.nome}.")
    }
    
    fun calcularPontos(): Int{
        var soma =0
        this.conteudos.forEach {
            soma+=it.pontos
        }
        return soma;
    }
}

fun main() {
    
    //alunos
    var alunos = mutableListOf(Usuario("Joao", Stack.JAVA),
                        Usuario("Maria", Stack.JAVA),
                        Usuario("Alice", Stack.KOTLIN),
                        Usuario("Rafael", Stack.KOTLIN),
                        Usuario("Mauricio", Stack.CSHARP),
                        Usuario("Zoraide", Stack.CSHARP))
    
    //conteudos na plataforma
    var javaB = ConteudoEducacional("Conceitos de JAVA" , 15,Stack.JAVA)
    var kotlinI = ConteudoEducacional("Oque é Kotlin" , 15,Stack.KOTLIN)
    var kotlinB = ConteudoEducacional("Algumas linhas de Kotlin" , 50,Stack.KOTLIN,100)
    var kotlinA = ConteudoEducacional("Kotlin Avançado" , 120,Stack.KOTLIN, 500 )
    var csharpI = ConteudoEducacional("C# Iniciante" , 120,Stack.KOTLIN,5 )
    
    //formações
    var javaDIO = Formacao("Desvendado JAVA", listOf(javaB) , Nivel.BASICO)
    var kotlinDIO = Formacao("Kotlin na pratica", listOf(kotlinI,kotlinB,kotlinA) , Nivel.AVANÇADO)
    var csharpDIO = Formacao("C# e dotNet", listOf(csharpI) , Nivel.INTERMEDIARIO)
    
    //incrição de alunos com interesses nas formações
    javaDIO.matricular(*alunos.filter{it.interesse == Stack.JAVA}.toTypedArray())
    kotlinDIO.matricular(*alunos.filter{it.interesse == Stack.KOTLIN}.toTypedArray())
    csharpDIO.matricular(*alunos.filter{it.interesse == Stack.CSHARP}.toTypedArray())
    
    //graduação dos alunos
    println("\n\nAlunos formados:")   
    javaDIO.inscritos.toList().forEach{
        javaDIO.formarUsuario(it) 
    }
    kotlinDIO.inscritos.toList().forEach{
        kotlinDIO.formarUsuario(it) 
    }
    csharpDIO.inscritos.toList().forEach{
        csharpDIO.formarUsuario(it) 
    }
    
    //printa pontuação de alunos formados
    println("\n\nTotal de pontos acumulado:")
    alunos.forEach{
        println("\t${it.nome} - ${it.pontuacao}.")
    }
}
