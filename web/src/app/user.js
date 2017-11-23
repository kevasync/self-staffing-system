"use strict";
var User = (function () {
    function User() {
        this.id = '';
        this.rankings = [];
    }
    User.prototype.getRankingBySkill = function (skill) {
        var existingRanking = this.findRanking(skill);
        return existingRanking ? existingRanking.rank : 0;
    };
    User.prototype.updateRanking = function (skill, value) {
        var existingRanking = this.findRanking(skill);
        if (existingRanking) {
            existingRanking.rank = value;
        }
        else {
            this.rankings.push({ skill: skill, rank: value });
        }
    };
    User.prototype.findRanking = function (skill) {
        return this.rankings.find(function (r) { return r.skill === skill; });
    };
    return User;
}());
exports.User = User;
//# sourceMappingURL=user.js.map