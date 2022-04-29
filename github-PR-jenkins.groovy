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
        stage('Approval') {
        // no agent, so executors are not used up when waiting for approvals
         when { changeset "vm-management/create-vm/**"}
        agent none
        steps {
            script {
                mail from: "$VM_EMAIL_FROM", to: "$VM_SUPPORT_EMAIL", subject: "APPROVAL REQUIRED FOR $JOB_NAME" , body: """Build $BUILD_NUMBER required an approval. Go to $BUILD_URL for more info."""
                def deploymentDelay = input id: 'Deploy', message: 'Deploy to production?', parameters: [choice(choices: ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24'], description: 'Hours to delay deployment?', name: 'deploymentDelay')]
                sleep time: deploymentDelay.toInteger(), unit: 'HOURS'
            }
        }
    }
    }
}
