<template>
  <div class="hello">
    <h1>Staff O' Matic</h1>
    <h3>powered by vue.js</h3>
    <div>
      <div>
        <h4>Pick a skill:</h4>
          <ul>
            <li v-for="c in filteredCategories" v-on:click="selectSkill(c)">{{ c }}</li>
          </ul>
      </div>
      <div><input type="text" v-model="categoryFilter" @change="filterCategories"></div>
      <div>
        <h3>{{ selectedSkills.length }} skills selected:</h3>
        <ul>
            <li v-for="s in selectedSkills">{{ s }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
let categories = ['js', 'html', 'java']
export default {
  name: 'HelloWorld',
  data () {
    return {
      counter: 0, 
      filteredCategories: categories,
      categoryFilter: '',
      selectedSkills: []
    }
  },
  methods: {
    filterCategories: function (event) {
      if(this.categoryFilter.length > 1) {
        this.filteredCategories = categories.filter(c => c.toLowerCase().startsWith(this.categoryFilter.toLowerCase()))
      }
      else {
       this.filteredCategories = categories 
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
