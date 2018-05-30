<template>
  <div id="app">
    <div class="sun"></div>
    <img class="logo" src="./assets/logo.png">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

#app .logo {
  transform: rotate(180deg);
}

#app .sun {
  width: 70px;
  height: 70px;
  border-radius: 100%;
  background-color: orange;
  display: inline-block;
  margin-left: -70px;
  margin-bottom: 130px;
}
</style>
