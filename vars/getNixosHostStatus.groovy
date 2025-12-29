String call(String host) {
    String hostStatus = sh script: "consul kv get 'jenkins/NixOS/hosts/${host}/status' || true", returnStdout: true
    return hostStatus
}
