pipeline {
    agent any 
    stages {
        stage('DEV') { 
            steps {
                echo "Dev stage"
            }
        }
        stage('QA') { 
            steps {
                echo "QA stage"
            }
        }
        stage('TEST') { 
            steps {
                echo "TEST stage"
            }
        }
        stage('PROD') { 
            steps {
                echo "PROD stage"
            }
        }
    }
}
