def BuildEditor(RepositoryDir) {

	dir(RepositoryDir) {              
          /* bat 'Setup.bat --force' */

          bat 'GenerateProjectFiles.bat'

          script {
               if (params.REBUILD_ENGINE) {
                    bat '''pushd
                    echo MSVSPATH=%MSVSPATH%
                            %comspec% /c ""%MSVSPATH%\\VC\\Auxiliary\\Build\\vcvarsall.bat"" amd64
                            "%MSVSPATH%\\MSBuild\\Current\\Bin\\msbuild.exe" UE4.sln /p:Configuration="Development Editor" /p:Platform=Win64 /t:Clean
                            popd'''
               }
          }

          bat '''echo MSVSPATH=%MSVSPATH%
              %comspec% /c ""%MSVSPATH%\\VC\\Auxiliary\\Build\\vcvarsall.bat"" amd64
              "%MSVSPATH%\\MSBuild\\Current\\Bin\\msbuild.exe" .\\Engine\\Source\\Programs\\UnrealBuildTool\\UnrealBuildTool.csproj /p:Configuration="Development" /t:Build
              '''

          bat '.\\Engine\\Build\\BatchFiles\\Build.bat ShaderCompileWorker Win64 Development -Quiet'

          bat '.\\Engine\\Build\\BatchFiles\\Build.bat UnrealLightmass Win64 Development -Quiet'

          bat '.\\Engine\\Build\\BatchFiles\\Build.bat UnrealHeaderTool Win64 Development -Quiet'

          bat '.\\Engine\\Build\\BatchFiles\\Build.bat UE4Editor Win64 Development -Quiet'

          bat '.\\Engine\\Build\\BatchFiles\\Build.bat BladeRAEditor Win64 Development -Quiet'


          /*
          bat '''pushd .\\Engine\\Binaries\\DotNET
              svn revert GitDependencies.exe
              popd
              pushd .\\Engine\\Source\\Programs\\BuildAgent\\bin\\Development
              svn revert *.dll
              popd'''
          */
    }
                
    bat 'echo Build Editor - fininsh...'
}

def BuildClient(RepositoryDir, BuildPath, Build_inprogress, Platform, Texture, TargetEnv, Pak_enable, Dist_Option, Additional_opt ){
    BuildClient( RepositoryDir, BuildPath, Build_inprogress, Platform, Texture, TargetEnv, Pak_enable, Dist_Option, Additional_opt, " -compressed " )
}

def BuildClient(RepositoryDir, BuildPath, Build_inprogress, Platform, Texture, TargetEnv, Pak_enable, Dist_Option, Additional_opt, Compressed ){

    dir(RepositoryDir) {
             
         def ProjectFilePath = BuildPath + "/BladeRA/BladeRA.uproject"
         bat """

             
             call .\\Engine\\Build\\BatchFiles\\RunUAT.bat BuildCookRun -project=$ProjectFilePath -Xlint -noP4 -clientconfig=$TargetEnv -compile -nocompileeditor -utf8output -platform=$Platform -targetplatform=$Platform -cookflavor=$Texture -build -cook -archive -archivedirectory=$Build_inprogress -unversionedcookedcontent $Pak_enable $Compressed -stage -package -SkipCookingEditorContent $Dist_Option $Additional_opt
                   
             """

    }
                
    bat 'echo Build and Package for Client - fininsh...'

}


def CopyBuildResult(RepositoryDir, Archive_dir, Archive_dir_export){

    dir(RepositoryDir) {
                
         bat """

             Robocopy  $Archive_dir $Archive_dir_export * /MIR /W:20 /R:15
             if %errorlevel% GTR 3 exit %errorlevel%
                    
             exit 0"""
         }
                
         bat 'echo Copy BuildResult - finish...'
}

def INI_Setting_For_Uncheater(RepositoryDir){

    dir(RepositoryDir) {
                
         bat '''pushd BladeRA\\Config
                powershell -Command "(gc DefaultGame.ini) -replace \'UseUncheater=False\', \'UseUncheater=True\' | Out-File DefaultGame.ini"
                echo "======================================================================"
                type DefaultGame.ini
                echo "======================================================================"
                popd'''
                    
    }
                
    bat 'echo INI Setting for Steam - fininsh...'
}

def INI_Revert(RepositoryDir, Game_svn_url, Game_Revision_ID ){

    dir(RepositoryDir) {
                
         bat """pushd .\\BladeRA\\Config
                del /Q /S /A *
             """
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
                                        ignoreExternalsOption: true, 
                                        local: 'BladeRA', 
                                        remote: Game_svn_url + '@' + Game_Revision_ID]],
                                        quietOperation: true, 
                                        workspaceUpdater: [$class: 'UpdateUpdater']])
                    
                    
    }
                
    bat 'echo INI Setting for Steam - fininsh...'
}

def Remove_Old_PackageFolder(RepositoryDir, Target_package_path ){

    dir(RepositoryDir) {

        def RemovePath = pwd() + "\\" + Target_package_path + "\\"
        bat """SET REMOVE_PATH=$RemovePath
            del /q "%REMOVE_PATH%*"
            FOR /D %%p IN ("%REMOVE_PATH%*.*") DO rmdir "%%p" /s /q
            exit 0"""
    }

}


return this