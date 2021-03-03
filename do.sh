#!/usr/bin/env bash

set -e

base_dir=.
src_dir=${base_dir}

red='\033[1;31m'
green='\033[1;32m'
normal='\033[0m'

## run : [<env>]
function task_run {

  local stage=$1
  echo $stage

  tenantId=$(az keyvault secret show --id "" --query value --out tsv | cat)
  clientsecret=$(az keyvault secret show --id "" --query value --out tsv | cat)
  clientId=$(az keyvault secret show --id "" --query value --out tsv | cat)

  mkdir src/main/resources/tmp

  sed -e "s|<<TENANT_ID>>|${tenantId}|g" \
      -e "s|<<CLIENT_SECRET>>|${clientsecret}|g" \
      -e "s|<<CLIENT_ID>>|${clientId}|g" \
  "src/main/resources/env/$stage.config.properties" > "src/main/resources/tmp/$stage.config.properties"

}

function task_usage {
    echo "Usage: $0"
    sed -n 's/^##//p' <$0 | column -t -s ':' |  sed -E $'s/^/\t/'
}

CMD=${1:-}
shift || true
RESOLVED_COMMAND=$(echo "task_"$CMD | sed 's/-/_/g')

if [ "$(LC_ALL=C type -t $RESOLVED_COMMAND)" == "function" ]; then
    pushd $(dirname "${BASH_SOURCE[0]}") >/dev/null
    $RESOLVED_COMMAND "$@"
else
    task_usage
fi

