def RepositoryDir = 'Repository'
def JenkinsScriptDir = 'JenkinsScriptDir'
def JenkinsScriptGitURL = 'http://repositories.actionsquare.corp:3000/b2r/Tools.git'
pipeline
{
    parameters
    {
        string defaultValue: 'http://repositories.actionsquare.corp/svn/b2r/trunk/BladeRA/Server/', description: '서버 SVN 주소', name: 'SERVER_SVN_URL', trim: false
        string defaultValue: 'HEAD', description: 'Server svn revision id', name: 'SERVER_REVISION_ID', trim: false
        booleanParam defaultValue: false, description: '리빌드여부', name: 'REBUILD_ENGINE'
    }

    agent
    {
        node
        {
            label 'TEST_LABEL'
        }
    }


    stages 
    { 
        stage('Server SVN CheckOut') 
        {      
            steps
            {
                    checkout([$class: 'SubversionSCM',
                        additionalCredentials: [],
                        excludedCommitMessages: '',
                        excludedRegions: '',
                        excludedRevprop: '',
                        excludedUsers: '',
                        filterChangelog: false,
                        ignoreDirPropChanges: false,
                        includedRegions: '',
                        locations: [[cancelProcessOnExternalsFail: true, 
                                        credentialsId: 'kknd8189', 
                                        depthOption: 'infinity', 
                                        ignoreExternalsOption: false, 
                                        local: '.', 
                                        remote: params.SERVER_SVN_URL + '@' + params.SERVER_REVISION_ID]], 
                                        quietOperation: true, 
                                        workspaceUpdater: [$class: 'UpdateWithRevertUpdater']])
            }
        }
    }

}