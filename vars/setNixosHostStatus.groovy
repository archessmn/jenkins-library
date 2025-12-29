def call(String host, String status) {
    hostStatus = sh script: "consul kv put 'jenkins/NixOS/hosts/${host}/status' ${status}", returnStdout: true
}
