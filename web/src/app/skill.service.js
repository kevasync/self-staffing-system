"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var service_1 = require("./service");
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var SkillService = (function (_super) {
    __extends(SkillService, _super);
    function SkillService(h) {
        var _this = _super.call(this, h) || this;
        _this.h = h;
        return _this;
    }
    SkillService.prototype.get = function () {
        return _super.prototype.getAll.call(this, 'skill');
    };
    return SkillService;
}(service_1.Service));
SkillService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], SkillService);
exports.SkillService = SkillService;
//# sourceMappingURL=skill.service.js.map