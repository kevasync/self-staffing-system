<template>
  <div class="hello">
    <h1>Staff O' Matic</h1>
    <div>
      <div>
        <h4>Tap to toggle skills:</h4>
          <ul>
            <li v-for="s in filteredSkills" v-on:click="selectSkill(s)">{{ s }}</li>
          </ul>
      </div>
      <div><input type="text" v-model="categoryFilter" @change="filterSkills"></div>
      <div v-if="selectedSkills.length > 0">
        <h3>{{ selectedSkills.length }} skills selected:</h3>
        <ul>

            <li v-for="s in selectedSkills">{{ s }}</li>
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
      categoryFilter: '',
      selectedSkills: []
    }
  },
  methods: {
    filterSkills: function (event) {
      if(this.categoryFilter.length > 1) {
        this.filteredSkills = skills.filter(c => c.toLowerCase().startsWith(this.categoryFilter.toLowerCase()))
      }
      else {
       this.filteredSkills = skills 
      } 
    },
    selectSkill: function(skill) {
      let i = this.selectedSkills.indexOf(skill)
      if(i == -1){
        this.selectedSkills.push(skill)  
      } else {
        this.selectedSkills.splice(i, 1)
      }
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
      skills = response.data.map(x => x.skill)
      this.filteredSkills = skills
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
</style>
