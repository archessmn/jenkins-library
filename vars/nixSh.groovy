def call(arg) {
    if (arg instanceof String) {
        return runString(arg)
    }
    if (arg instanceof Map) {
        return runMap(arg)
    }
    error 'nixSh only accepts String or Map (like sh)'
}

private def runString(String script) {
    def result = sh buildCommand(script)
    return result
}

private def runMap(Map args) {
    if (!args.script) {
        error "nixSh requires 'script'"
    }

    def newArgs = args.clone()
    newArgs.script = buildCommand(args.script)

    def result = sh newArgs
    return result
}

private String getSystem() {
    def _cachedSystem = sh(
        script: 'nix config show system',
        returnStdout: true
    ).trim()
    return _cachedSystem
}

private String buildCommand(String script) {
    def system = env.NIX_SYSTEM ?: getSystem()

    if (!system) {
        error 'Unable to determine Nix system'
    }

    return "nix shell .#devShells.${system}.default --command ${script}"
}
