# 📦 Set up environment variables

> Workflow that set up the environment variables such as branch , env and tag based on automatic or manual procedure.

## 🚀 Features

-Environment , branch and tag are going to be set up according to sthe following scenarios : 

Scenario: Calculating the environment and branch automatically
             When the code into the <branch> is merged
             Then the <branch> is taken from github and set as a BRANCH variable with the value
              And the <environment> is calculated automatically according to the <branch>
              
        Examples:
                  | environment | branch  |
                  | develop     | develop |
                  | qa          | master  |
        
Scenario: Calculating the environment manually and setting the branch automatically
            Given the user is executing the workflow manually
              And the user provides one of the following <environments> as input
              
                  | environment |
                  | develop     |
                  | qa          |
                  
             When the user hits the button start
             Then the workflow identifies that the action was manual
              And sets the <environment> value in the workflow
              And sets the <branch> value in the workflow
        Examples:
                  | environment | branch  |
                  | develop     | develop |
                  | qa          | master  |
            
            
Scenario: Setting the environment and branch manually
            Given that the environment and tag were set manually
             When the user hits the button start
             Then the workflow identifies that the action was manually added
              And sets the <environment> value in the workflow
              And sets the <branch> value in the workflow
              
        Examples:
                  | environment | tag    |
                  | stg         | 1.1.0  |
                  | LT          | hotfix |
                  | STG         | 1.1.0  |

## 💡 Usage

Example usage:

When trigger manually selecting QA env , then main branch should be associated with this process : 

  echo "Job running on qa" echo "BRANCH running on main" echo "TAG running on "
  shell: /usr/bin/bash -e {0}
  env:
    GLOBAL_ENV: qa
    GLOBAL_BRANCH: main
    GLOBAL_TAG: 


## 📷 Screenshots

_ Running manually selecting QA environment , should associate main branch in that case : 

<img width="353" alt="Screenshot 2025-06-04 at 9 45 18 AM" src="https://github.com/user-attachments/assets/b86887d4-1f10-4951-a609-bc8c47c9ffc0" />

<img width="702" alt="Screenshot 2025-06-04 at 9 44 55 AM" src="https://github.com/user-attachments/assets/3b0828f2-9e1d-45f6-9baa-9a056e499591" />

