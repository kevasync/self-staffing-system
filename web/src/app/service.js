"use strict";
var http_1 = require("@angular/http");
require("rxjs/add/operator/toPromise");
var Service = (function () {
    function Service(http) {
        this.http = http;
        this.baseUrl = 'http://localhost:5984';
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
    }
    Service.prototype.get = function (resource, id) {
        return this.http.get(this.baseUrl + "/" + resource + "/" + id)
            .toPromise()
            .then(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    Service.prototype.getAll = function (resource) {
        return this.http.get(this.baseUrl + "/" + resource + "/_all_docs?include_docs=true")
            .toPromise()
            .then(function (response) {
            var r = response.json();
            return r.rows.map(function (r) { return r.doc; });
        })
            .catch(this.handleError);
    };
    Service.prototype.update = function (item, resource, id) {
        this.http.put(this.baseUrl + "/" + resource + "/" + id, JSON.stringify(item), { headers: this.headers })
            .toPromise()
            .then(function () { return item; })
            .catch(this.handleError);
    };
    Service.prototype.handleError = function (error) {
        console.error('an error occured', error);
        return Promise.reject(error.message || error);
    };
    return Service;
}());
exports.Service = Service;
//# sourceMappingURL=service.js.map