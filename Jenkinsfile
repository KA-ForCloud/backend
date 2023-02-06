pipeline {
    agent any
    tools {
        gradle 'gradle'
    }
    stages {
        stage('Git Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/KA-ForCloud/backend.git'
            }
        }
        stage('BE-Build') {
            steps {
                    sh "./gradlew clean build --exclude-task test"
                    slackSend (channel: '#jenkins-alert', color: '#FFFF00', message: "Backend Build Complete: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploy Start"
                sshagent(credentials: ['kic_key']) {
                    echo "sshagent start"
                    sh ''
                        ssh -o StrictHostKeyChecking=no centos@210.109.62.6 uptime
                        scp /var/jenkins_home/workspace/forCloud_Backend_Pipeline/build/libs/backend-0.0.1-SNAPSHOT.jar centos@210.109.62.6:/home/centos/Backend
                    ''
                    
                    sh 'JENKINS_NODE_COOKIE=dontKillMe ssh -t centos@210.109.62.6 ./deploy.sh > /dev/null 2>&1 &'
                    
                    echo "Success"
                    slackSend (channel: '#jenkins-alert', color: '#FFFF00', message: "Backend Deploy Complete: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                }
            }
        }
    }
}
