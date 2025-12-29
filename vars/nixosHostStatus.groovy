def get(String host) {
    hostStatus = sh script: "consul kv get 'jenkins/NixOS/hosts/${host}/status' || true", returnStdout: true
    return hostStatus
}

def set(String host, String status) {
    hostStatus = sh script: "consul kv put 'jenkins/NixOS/hosts/${host}/status' ${status}", returnStdout: true
}
