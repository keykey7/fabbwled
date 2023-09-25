package ch.bbw.fabbwled.lands.book.yaml;

import ch.bbw.fabbwled.lands.exception.FabledTechnicalException;

import java.util.List;

public class YamlActionReachabilityHelper {
    public static YamlReachabilityResult verifyActionList(List<Action> actions) {
        var diverges = false;

        for (Action action : actions) {
            if (diverges) {
                throw new FabledTechnicalException(
                        "Unreachable action: " + action + ". This action is after a terminating action like turnTo");
            }
            var result = action.verifyReachability();
            diverges = result.diverges();
        }

        return diverges ? YamlReachabilityResult.DIVERGES : YamlReachabilityResult.NORMAL;
    }
}
