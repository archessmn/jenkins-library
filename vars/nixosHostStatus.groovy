def get(LinkedHashMap params) {
    hostStatus = sh script: "consul kv get 'jenkins/NixOS/hosts/${params.host}/status' || true", returnStdout: true
    return hostStatus
}

def set(LinkedHashMap params) {
    hostStatus = sh script: "consul kv put 'jenkins/NixOS/hosts/${params.host}/status' ${params.status}", returnStdout: true
}
