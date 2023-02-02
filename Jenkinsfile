pipeline {
    agent any
    tools {
        gradle 'gradle'
    }
    stages {
        stage('Git Clone') {
            steps {
                git branch: 'cicdtest', url: 'https://github.com/KA-ForCloud/backend.git'
            }
        }
        stage('BE-Build') {
            steps {
                    sh "./gradlew clean build --exclude-task test"
            }
        }
        stage('Deploy') {
            steps {
                sshagent(credentials: ['kic_key']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no centos@210.109.62.6 uptime
                        scp /var/jenkins_home/workspace/forCloud_Backend_Pipeline/build/libs/backend-0.0.1-SNAPSHOT.jar centos@210.109.62.6:/home/centos/Backend
                        ssh -t ubuntu@210.109.62.6 ./deploy.sh
                    '''
                }
            }
        }
    }
}
