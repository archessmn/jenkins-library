void call(String host, String status) {
    sh script: "consul kv put 'jenkins/NixOS/hosts/${host}/status' ${status}", returnStdout: true
}
