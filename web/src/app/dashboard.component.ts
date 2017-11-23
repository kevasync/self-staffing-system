import { Component, OnInit } from '@angular/core'

import { Skill } from './skill'
import { User } from './user'
import { Team } from './team'
import { Location } from './location'
import { SkillService } from './skill.service'
import { UserService } from './user.service'
import { LocationService } from './location.service'
import { TeamService } from './team.service'

@Component({
  selector: 'my-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})

export class DashboardComponent implements OnInit {

  skills: Skill[]
  currentUser: User
  userJson: string
  locations: Location[]
  teams: Team[]

  constructor(private skillService: SkillService,
              private userService: UserService,
              private locationService: LocationService,
              private teamService: TeamService,
              ) { }

  ngOnInit(): void {
    this.currentUser = new User()
    this.skillService.get().then(r => this.skills = r)
    this.locationService.get().then(r => this.locations = r)
    this.teamService.get().then(r => this.teams = r)
  }

  userChanged(userId: string): void {
    this.currentUser.id = userId
  }

  incrementSkill(id: number, oldValue: number): void {
    this.currentUser.updateRanking(id, this.currentUser.getRankingBySkill(id) + 1)
  }
  
  decrementSkill(id: number, oldValue: number): void {
  	this.currentUser.updateRanking(id, this.currentUser.getRankingBySkill(id) - 1)
  }

  locationChanged(locationId: number): void {
    this.currentUser.location = locationId  
  }

  teamChanged(teamId: number): void {
    this.currentUser.team = teamId  
  }

  save(): void {
    this.userService.update(this.currentUser)
  }
}
