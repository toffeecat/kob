<template>
    <div class="container">
      <div class="row">
        <div class="col-2">
          <div class="card" style="margin-top: 20px;">
            <div class="card-body">
              <img :src="$store.state.user.photo" alt="" style="width: 80%;" />
            </div>
          </div>
        </div>
        <div class="col-9">
          <div class="card" style="margin-top: 20px;">
            <div class="card-header">
              <span style="font-size: 120%">my bot</span>
              <button type="button" class="btn btn-primary float-end" data-bs-toggle="modal" data-bs-target="#add-bot-btn">
                Create Bot
              </button>
            </div>
            <div class="modal fade" id="add-bot-btn" tabindex="-1" >
              <div class="modal-dialog modal-xl">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title" id="exampleModalLabel">Create Bot</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                        <form>
                            <div class="mb-3">
                                <label for="add-bot-title" class="form-label">Bot name</label>
                                <input type="text" class="form-control" id="add-bot-title" placeholder="Please input bot name">
                            </div>
                            <div class="mb-3">
                                <label for="add-bot-description" class="form-label">Bot Description</label>
                                <textarea class="password"  id="exampleInputPassword1" rows="3" placeholder="Please input bot description"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="add-bot-code" class="form-label">Bot Code</label>
                                <textarea class ="password" id="add-bot-code" rows="7" placeholder="Please input bot code"></textarea>
                            </div>
                        </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                  </div>
                </div>
              </div>
            </div>
            <div class="card-body">
              <table class="table table-striped table-hover">
                <thead>
                  <tr>
                    <th>name</th>
                    <th>create time</th>
                    <th>operation</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="bot in bots" :key="bot.id">
                    <td>{{ bot.title }}</td>
                    <td>{{ bot.createtime }}</td>
                    <td>
                      <button type="button" class="btn btn-secondary" style="margin-right: 10px">Alter</button>
                      <button type="button" class="btn btn-danger">Delete</button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
    
<script>
import { ref } from 'vue'
import $ from 'jquery'
import { useStore } from 'vuex'

export default {
    setup() {
        const store = useStore();
        let bots = ref([])
        const refresh_bots = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/getlist/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    bots.value = resp;
                }
            })
        }

        refresh_bots();
        return {
            bots
        }
    }
}
</script>
    
<style scoped>
</style>