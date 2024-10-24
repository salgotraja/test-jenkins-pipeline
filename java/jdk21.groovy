pipeline {
    agent any
    
    tools {
        maven "M3"
    }
    
    parameters {
        choice(name: 'BRANCH', choices: ['main', 'dev', 'staging', 'production'], description: 'Select the branch to build')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout the selected branch
                    git branch: "${params.BRANCH}", url: 'https://github.com/salgotraja/spring-boot-microservices.git'
                }
            }
        }

        stage('Build') {
            steps {
                pipeline {
    agent any
    
    tools {
        maven "M3"
    }
    
    parameters {
        choice(name: 'BRANCH', choices: ['main', 'dev', 'staging', 'production'], description: 'Select the branch to build')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout the selected branch
                    git branch: "${params.BRANCH}", url: 'https://github.com/salgotraja/spring-boot-microservices.git'
                }
            }
        }

        stage('Build') {
            steps {
               sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}

            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
