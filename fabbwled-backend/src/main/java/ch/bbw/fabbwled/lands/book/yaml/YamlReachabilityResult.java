package ch.bbw.fabbwled.lands.book.yaml;

public enum YamlReachabilityResult {
    NORMAL,
    /**
     * This action diverges (does not return, meaning it's a terminating action like turnTo).
     */
    DIVERGES;

    /**
     * The intersection of the two results, returns DIVERGES if both results diverge.
     */
    public YamlReachabilityResult intersection(YamlReachabilityResult other) {
        if (this == YamlReachabilityResult.DIVERGES && other == YamlReachabilityResult.DIVERGES) {
            return YamlReachabilityResult.DIVERGES;
        }
        return YamlReachabilityResult.NORMAL;
    }

    public boolean diverges() {
        return this == YamlReachabilityResult.DIVERGES;
    }
}