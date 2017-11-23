"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var user_1 = require("./user");
var skill_service_1 = require("./skill.service");
var user_service_1 = require("./user.service");
var location_service_1 = require("./location.service");
var team_service_1 = require("./team.service");
var DashboardComponent = (function () {
    function DashboardComponent(skillService, userService, locationService, teamService) {
        this.skillService = skillService;
        this.userService = userService;
        this.locationService = locationService;
        this.teamService = teamService;
    }
    DashboardComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.currentUser = new user_1.User();
        this.skillService.get().then(function (r) { return _this.skills = r; });
        this.locationService.get().then(function (r) { return _this.locations = r; });
        this.teamService.get().then(function (r) { return _this.teams = r; });
    };
    DashboardComponent.prototype.userChanged = function (userId) {
        this.currentUser.id = userId;
    };
    DashboardComponent.prototype.incrementSkill = function (id, oldValue) {
        this.currentUser.updateRanking(id, this.currentUser.getRankingBySkill(id) + 1);
    };
    DashboardComponent.prototype.decrementSkill = function (id, oldValue) {
        this.currentUser.updateRanking(id, this.currentUser.getRankingBySkill(id) - 1);
    };
    DashboardComponent.prototype.locationChanged = function (locationId) {
        this.currentUser.location = locationId;
    };
    DashboardComponent.prototype.teamChanged = function (teamId) {
        this.currentUser.team = teamId;
    };
    DashboardComponent.prototype.save = function () {
        this.userService.update(this.currentUser);
    };
    return DashboardComponent;
}());
DashboardComponent = __decorate([
    core_1.Component({
        selector: 'my-dashboard',
        templateUrl: './dashboard.component.html',
        styleUrls: ['./dashboard.component.css']
    }),
    __metadata("design:paramtypes", [skill_service_1.SkillService,
        user_service_1.UserService,
        location_service_1.LocationService,
        team_service_1.TeamService])
], DashboardComponent);
exports.DashboardComponent = DashboardComponent;
//# sourceMappingURL=dashboard.component.js.map