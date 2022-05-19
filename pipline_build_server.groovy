def RepositoryDir = 'Repository'
def JenkinsScriptDir = 'JenkinsScriptDir'
def JenkinsScriptGitURL = 'http://repositories.actionsquare.corp:3000/b2r/Tools.git'
pipeline
{
    parameters
    {
        string defaultValue: 'http://repositories.actionsquare.corp/svn/b2r/trunk/BladeRA/Server/', description: '서버 SVN 주소', name: 'SEVER_SVN_URL', trim: false
        string defaultValue: 'HEAD', description: 'Game svn revision id', name: 'GAME_SRC_REVISION_ID', trim: false
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
                dir(RepositoryDir) {

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
                                        credentialsId: '_b2r_jenkins', 
                                        depthOption: 'infinity', 
                                        ignoreExternalsOption: false, 
                                        local: '.', 
                                        remote: params.ENGINE_SVN_URL + '@' + params.ENGINE_REVISION_ID]], 
                                        quietOperation: true, 
                                        workspaceUpdater: [$class: 'UpdateWithRevertUpdater']])

                    bat 'echo Engine Git checkout - fininsh...'
                }
        }

}