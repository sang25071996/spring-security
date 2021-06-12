pipeline {
    agent any

    tools {
        maven "Apache Maven 3.8.1"
    }
    stages {

        stage ("Clone") {
            steps {
                git credentialsId: 'git_credentials', url: 'https://github.com/sang25071996/spring-security.git'
            }
        }

        stage("build") {

            steps {
                echo 'Building the application.........'
                sh 'mvn clean install -Dspring.profiles.active=dev'
            }
        }

        stage("deploy") {
            steps {
                echo 'Deploying application.........'
            }
        }
    }
    post{
        always{
            echo "====++++always++++===="
        }
        success{
            echo "====++++only when successful++++===="
        }
        failure{
            echo "====++++only when failed++++===="
        }
    }
}