pipeline {
    agent any
    tools {
        maven "maven"
    }

    environment {
        APP_NAME = "spring-docker-cicd"
        RELEASE_NO = "1.0.0"
        DOCKER_USER = "trinadhrayala"
    }

    stages {

        stage("SCM checkout") {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rayalatrinadh/jenkins-ci-cd.git']])
            }
        }

        stage("Build Process") {
            steps {
                script {
                    bat 'mvn clean install'
                }
            }
        }

        stage("Build Image") {
            steps {
                script {
                    def imageName = "${DOCKER_USER}/${APP_NAME}".toLowerCase()
                    def imageTag = "${RELEASE_NO}-${BUILD_NUMBER}"
                    bat "docker build -t ${imageName}:${imageTag} ."
                }
            }
        }

        stage("Deploy Image to Docker Hub") {
            steps {
                script {
                    def imageName = "${DOCKER_USER}/${APP_NAME}".toLowerCase()  // Ensure imageName is lowercase
                    def imageTag = "${RELEASE_NO}-${BUILD_NUMBER}"
                    // Pass variables directly without ${} syntax
                    bat "docker run -d -p 9098:8086 ${imageName}:${imageTag}"
                }
                withCredentials([string(credentialsId: 'wa', variable: 'wa')]) {
                    script {
                        def imageName = "${DOCKER_USER}/${APP_NAME}".toLowerCase()
                        def imageTag = "${RELEASE_NO}-${BUILD_NUMBER}"
                        bat """
                            echo ${wa} | docker login -u trinadhrayala --password-stdin
                            docker push ${imageName}:${imageTag}
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            emailext attachLog: true,
            body: '''<html>
            <body>
              <p>Build Status: ${BUILD_STATUS}</p>
              <p>Build Number: ${BUILD_NUMBER}</p>
              <p>Check the <a href="${BUILD_URL}">Console output</a>.</p>
            </body>
            </html>''',
            mimeType: 'text/html',
            replyTo: '3nadhmail@gmail.com',
            subject: 'Pipeline Status : ${BUILD_NUMBER}',
            to: '3nadhmail@gmail.com'
        }
    }
}
