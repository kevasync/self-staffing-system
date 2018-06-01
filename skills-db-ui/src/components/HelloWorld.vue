<template>
  <div class="hello">
    <h1>Staff o' Matic</h1>
    <div>
      <div>
        <h3>Tap to Toggle Skills:</h3>
          <ul class="scrollingList">
            <li v-for="s in filteredSkills" v-on:click="selectSkill(s)">{{ s.skill }}</li>
          </ul>
      </div>
      
      <div>
        <h3>Filter Skills:</h3>
        <div>
          <span>By name: <input type="text" v-model="skillFilter" v-on:keyup="filterSkills"></span>
        </div>
        <div>
          <div>By catagory:</div>
          <ul class="scrollingList categoryFilterList">
              <li v-for="c in categories" v-on:click="filterOnCategory(c)">{{ c }}</li>
          </ul>
          <div v-if="skillFilter != ''">
            <h4 class="filterIndicator">Filtered by {{ skillFilter }}</h4>
          </div>
          <div v-if="categoryFilter != ''">
            <h4 class="filterIndicator">Filtering by category {{ categoryFilter }}</h4>  
          </div>
          <div v-if="!(categoryFilter == '' && skillFilter == '')">
            <h3 v-on:click="clearFilter()">Clear filter</h3>
          </div>
        </div>
      </div>
      
      <div v-if="selectedSkills.length > 0">
        <h3>{{ selectedSkills.length }} skill(s) selected:</h3>
        <ul>
            <li v-for="s in selectedSkills">{{ s.skill }}</li>
        </ul>
        <span v-on:click="selectedSkills = []">Clear skills...</span>
        <div>
          <h3 v-on:click="getSuggestions()">Get suggestions...</h3>
        </div>
        <div v-if="suggestedPeople.length > 0">
          <h4>Suggestions:</h4>
          <ul>
            <li v-for="p in suggestedPeople">{{ p.firstName }} {{ p.lastName }}</li>
          </ul>
        </div>
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
      skillFilter: '',
      selectedSkills: [],
      categoryFilter: '',
      categories: [],
      suggestedPeople: []
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
      this.suggestedPeople = []
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
      this.categoryFilter = ''
      this.skillFilter = ''
    },
    getSuggestions: function() {
      axios.post(`http://localhost:8081/suggestions/top5`, 
      {
        skillIds: this.selectedSkills.map(x => x.id),
        headers: { 
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods' : 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        }
      })
      .then(response => { 
        if(response.data.length > 0) {
          this.suggestedPeople = response.data
        } else {
          this.suggestedPeople = [{firstName: 'No one matched your criteria', lastName: '', id: -1}]
        }
      })
      .catch(e => console.log(e))
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

.scrollingList {
  max-height: 300px;
  overflow: hidden;
  overflow-y: scroll;
}
</style>
