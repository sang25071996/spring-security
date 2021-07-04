def gitUrl = "https://github.com/sang25071996/spring-security.git";
def gitTagVersion = "";
def email = "";

pipeline {
    agent any

    tools {
        maven "Apache Maven 3.8.1"
    }
    stages {

        // stage ("Get Version") {
        //     steps {
        //         script {
        //             def gitBranch = "${env.BRANCH_NAME}";
        //             println("git branch: ${gitBranch}")
        //         }
        //     }
        // }

        stage ("Checkout") {
            steps {
                checkout([$class: 'GitSCM', 
                branches: [[name: '*/master']], 
                extensions: [[$class: 'PerBuildTag'], 
                [$class: 'CleanBeforeCheckout', deleteUntrackedNestedRepositories: true]], 
                userRemoteConfigs: [[
                    credentialsId: 'git_credentials', 
                    url: "${gitUrl}",
                    refspec: '+refs/tags/*:refs/remotes/origin/tags/*',
                ]]
            ])

                echo 'Clone code........'
                git credentialsId: 'git_credentials', url: 'https://github.com/sang25071996/spring-security.git'
            }
        }

        // stage("build") {

        //     steps {
        //         echo 'Building the application.........'
        //         sh 'mvn clean install -Dspring.profiles.active=dev'
        //         sh 'docker --version'
        //         echo "Build number: ${BUILD_NUMBER}"
        //         sh 'ls -a'
        //         sh 'docker build -t spring-uaa-service:1.${BUILD_NUMBER} .'
        //         sh 'docker login -u sangqn96 -p 01626524426'
        //         sh 'docker tag spring-uaa-service:1.${BUILD_NUMBER} sangqn96/spring-uaa-service:1.${BUILD_NUMBER}'
        //         sh 'docker push sangqn96/spring-uaa-service:1.${BUILD_NUMBER}'
        //     }
        // }

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