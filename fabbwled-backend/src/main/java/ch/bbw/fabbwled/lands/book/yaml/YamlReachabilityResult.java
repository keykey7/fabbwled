package ch.bbw.fabbwled.lands.book.yaml;

public enum YamlReachabilityResult {
    NORMAL,
    /**
     * This action terminates (does not return, meaning it's a terminating action like turnTo).
     */
    TERMINATES;

    /**
     * The intersection of the two results, returns TERMINATES if both results diverge.
     */
    public YamlReachabilityResult intersection(YamlReachabilityResult other) {
        if (this == YamlReachabilityResult.TERMINATES && other == YamlReachabilityResult.TERMINATES) {
            return YamlReachabilityResult.TERMINATES;
        }
        return YamlReachabilityResult.NORMAL;
    }

    public boolean terminates() {
        return this == YamlReachabilityResult.TERMINATES;
    }
}