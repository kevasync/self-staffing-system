<template>
  <div class="hello">
    <h1>Staff O' Matic</h1>
    <div>
      <div>
        <h3>Tap to toggle skills:</h3>
          <ul>
            <li v-for="s in filteredSkills" v-on:click="selectSkill(s)">{{ s.skill }}</li>
          </ul>
      </div>
      <h3>Filter skills</h3>
      <div>
        <span>By Name: <input type="text" v-model="skillFilter" v-on:keyup="filterSkills"></span>
      </div>
      <div>
        By catagory:
        <ul class="categoryFilterList">
            <li v-on:click="clearFilter()">Clear filter</li>
            <li v-for="c in categories" v-on:click="filterOnCategory(c)">{{ c }}</li>
        </ul>
        <span class="filterIndicator" v-if="skillFilter !== undefined">Filtering skills by {{ skillFilter }}</span>
        <span class="filterIndicator" v-if="categoryFilter !== undefined">Filtering by category {{ categoryFilter }}</span>
      </div>
      <div v-if="selectedSkills.length > 0">
        <h3>{{ selectedSkills.length }} skills selected:</h3>
        <ul>
            <li v-for="s in selectedSkills">{{ s.skill }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
var skills = []

export default {
  name: 'HelloWorld',
  data () {
    return {
      counter: 0, 
      filteredSkills: [],
      skillFilter: undefined,
      selectedSkills: [],
      categoryFilter: undefined,
      categories: []
    }
  },
  methods: {
    filterSkills: function (event) {
      if(this.skillFilter.length > 0) {
        this.filteredSkills = skills.filter(s => s.skill.toLowerCase().startsWith(this.skillFilter.toLowerCase()))
      }
      else {
       this.filteredSkills = skills 
      }
      if(categoryFilter !== undefined)  {
       this.filteredSkills = this.filterSkills.filter(s => s.category.toLowerCase() == category)  
      }
    },
    selectSkill: function(skill) {
      let i = this.selectedSkills.indexOf(skill)
      if(i == -1){
        this.selectedSkills.push(skill)  
      } else {
        this.selectedSkills.splice(i, 1)
      }
    },
    filterOnCategory: function(category) {
      console.log(category)
      this.filteredSkills = skills.filter(s => s.category.toLowerCase() == category)
      if(this.skillFilter.length > 0) {
        this.filteredSkills = this.filteredSkills.filter(s => s.skill.toLowerCase().startsWith(this.skillFilter.toLowerCase()))
      }  
      this.categoryFilter = category
    },
    clearFilter: function() {
      this.filteredSkills = skills
      this.categoryFilter = undefined
      this.skillFilter = undefined
    }
  },
  created: function() {
    axios.get(`http://localhost:8081/skills/skills`, {
      headers: { 
      'Access-Control-Allow-Origin' : '*',
      'Access-Control-Allow-Methods' : 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
      }
    })
    .then(response => { 
      skills = response.data
      this.filteredSkills = skills
      this.categories = Array.from(new Set(skills.map(x => x.category))).sort()
    })
    .catch(e => console.log(e))
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.categoryFilterList {
  display: inline;
}
.filterIndicator {
  display: block;
}
</style>
