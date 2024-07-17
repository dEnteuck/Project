package vlu.android.project;

public class Match {
    int teamLogo1, teamLogo2;
    String teamName1, teamName2, teamScore1, teamScore2, matchStatus;

    public Match(int teamLogo1, int teamLogo2, String teamName1, String teamName2, String teamScore1, String teamScore2, String matchStatus) {
        this.teamLogo1 = teamLogo1;
        this.teamLogo2 = teamLogo2;
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
        this.teamScore1 = teamScore1;
        this.teamScore2 = teamScore2;
        this.matchStatus = matchStatus;
    }

    public Match() {
    }

    public int getTeamLogo1() {
        return teamLogo1;
    }

    public void setTeamLogo1(int teamLogo1) {
        this.teamLogo1 = teamLogo1;
    }

    public int getTeamLogo2() {
        return teamLogo2;
    }

    public void setTeamLogo2(int teamLogo2) {
        this.teamLogo2 = teamLogo2;
    }

    public String getTeamName1() {
        return teamName1;
    }

    public void setTeamName1(String teamName1) {
        this.teamName1 = teamName1;
    }

    public String getTeamName2() {
        return teamName2;
    }

    public void setTeamName2(String teamName2) {
        this.teamName2 = teamName2;
    }

    public String getTeamScore1() {
        return teamScore1;
    }

    public void setTeamScore1(String teamScore1) {
        this.teamScore1 = teamScore1;
    }

    public String getTeamScore2() {
        return teamScore2;
    }

    public void setTeamScore2(String teamScore2) {
        this.teamScore2 = teamScore2;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }
}
