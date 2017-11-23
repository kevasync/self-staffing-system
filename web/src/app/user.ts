import { Ranking } from './ranking';

export class User {
	id: string
	location: number
	team: number
	rankings: Ranking[]

	constructor() {
		this.id = ''
		this.rankings = []
	}

	getRankingBySkill(skill: number): number {
		let existingRanking = this.findRanking(skill)
		return existingRanking ? existingRanking.rank : 0
	}

	updateRanking(skill: number, value: number): void {
		let existingRanking = this.findRanking(skill)
		if(existingRanking) {
			existingRanking.rank = value
		} else {
			this.rankings.push({ skill: skill,  rank: value })
		}
	}

	private findRanking(skill: number): Ranking{
		return this.rankings.find(r => r.skill === skill)
	}
}	