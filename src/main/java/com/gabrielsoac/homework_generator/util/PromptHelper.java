package com.gabrielsoac.homework_generator.util;

public class PromptHelper {
    
    public String createPromptForGenerateHomeWork(String text){
        return """
            Você é um professor especialista na criação de material didático. Sua tarefa é ler o texto fornecido abaixo e gerar um dever de casa com questões de múltipla escolha baseadas exclusivamente nele.

            **REGRAS E RESTRIÇÕES ABSOLUTAS:**
            1. Você deve retornar **EXCLUSIVAMENTE** um objeto JSON válido como texto. 
            2. Não adicione nenhuma formatação Markdown (como ` ```json ` ou ` ``` `). 
            3. Não inclua saudações, introduções, notas, comentários ou qualquer texto fora do JSON. Apenas o JSON puro.
            4. O JSON deve seguir estritamente a estrutura e as chaves do template abaixo.
            5. Crie no mínimo 10 questões

            **TEMPLATE DO JSON ESPERADO:**
            {
            "title": "Gere um título adequado e criativo baseado no texto recebido",
            "questions": [
                {
                "summary": "Texto da pergunta gerada",
                "number": 1,
                "options": {
                    "a": "Texto da primeira opção",
                    "b": "Texto da segunda opção",
                    "c": "Texto da terceira opção",
                    "d": "Texto da quarta opção"
                },
                "correctAnswer": "a",
                "explain": "Explicação detalhada de por que esta opção é a correta"
                },
                {
                "summary": "Texto da pergunta gerada",
                "number": 2,
                "options": {
                    "a": "Texto da primeira opção",
                    "b": "Texto da segunda opção",
                    "c": "Texto da terceira opção",
                    "d": "Texto da quarta opção"
                },
                "correctAnswer": "c",
                "explain": "Explicação detalhada de por que esta opção é a correta"
                }
            ]
            }

            **TEXTO:**
                """ + text;
    }
}
