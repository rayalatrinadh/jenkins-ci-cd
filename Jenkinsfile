pipeline{

    agent any
    tools{
        maven "maven"
    }
    stages{

        stage("SCM checkout"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rayalatrinadh/jenkins-ci-cd.git']])
            }
        }

        stage("Build Process"){
            steps{
                script{
                    bat 'mvn clean install'
                }
            }
        }



//         stage("Deploy War to Container"){
//             steps{
//                 deploy adapters: [tomcat9(credentialsId: 'c5445457-d129-4abe-99ba-1e9715e48a5b', path: '', url: 'http://localhost:9090/')], contextPath: 'jenkinsCiCd', war: '**/*.war'
//             }
//         }


    }

    post{
        always{
            emailext attachLog: true,
            body: '''<html>
            <body>
              <p>Build Status: ${BUILD_STATUS}</p>
                <p>Build Number: ${BUILD_NUMBER}</p>
                 <p>Check the <a href="${BUILD_URL}">Console output</a>.</p>
             </body>
            </html>''', mimeType: 'text/html', replyTo: '3nadhmail@gmail.com', subject: 'Pipeline Status : ${BUILD_NUMBER}', to: '3nadhmail@gmail.com'
        }
    }

}