<template>
  <div class="container">
	<div class="row mt-5">
            <div class="col-sm-2"></div>

            <div class="col-sm-8 p-5 rounded border" style="background-color: antiquewhite;">
                <div class="row p-2">
                    <div class="col-sm-6 p-4">
                        <label for="from">From</label>
                        <select id="from" v-model="fr" class="custom-select">
                            <option v-for="v in values" v-bind:key="v"> {{ v }} </option>
                        </select>
                    </div>
                    <div class="col-sm-6 p-4">
                        <label for="to">To</label>
                        <select id="to" v-model="to" class="custom-select">
                            <option v-for="v in values" v-bind:key="v"> {{ v }} </option>
                        </select>
                    </div>
                </div>

                <div class="row p-2">
                    <div class="col-sm-3 p-4"></div>

                    <div class="col-sm-6 p-4 text-center">
                        <form>
                            <div class="form-group">
                                <label htmlFor="vtoconv">Value to convert</label>
                                <input v-model="cost" type="text" class="form-control" id="vtoconv" placeholder="Enter value to convert"/>
                                    <button v-on:click="convert" type="button" class="btn btn-info mt-4">convert
                                    </button>
                            </div>
                        </form>
                        <h5 class="text-secondary font-weight-light mt-5"> {{info}}</h5>
                    </div>
                    <div class="col-sm-3 p-4"></div>
                </div>

                <div v-if="present" class="row p-2">
                    <div class="col-sm-12 text-center text-info font-weight-bolder font-italic p-4">
                        <h2>
                            Result: {{amount}}
                        </h2>
                    </div>
                </div>
            </div>

            <div class="col-sm-2"></div>
        </div>
  </div>
</template>

<script>
export default {
  name: 'Converter',
  data () {
            return {
                values: [],
                cost: null,
                fr: "",
                to: "",
                present: false,
                amount: undefined,
                info: ""
            }
        },
        //Alla creazione recupero le valute gestite dal sistema
        created () {
            fetch('http://localhost:8080/values')
                .then(response => response.json())
                .then(json => {
                    this.values = json
                })
        },
  methods: {
            convert: async function () {
                    if(this.fr != this.to && this.cost != null){
                        this.amount = await this.getAmount();
                        this.present = this.amount !== undefined;

                        if(!this.present)
                            this.info = "Internal server error";
                        else
                            this.info = "Successful conversion";
                    }else{
                        this.info = "Choose different currencies";
                        this.present = false;
                    }
            },
            getAmount: async function () {
                    return fetch('http://localhost:8080/convert?cost=' + this.cost + '&from=' + this.fr + '&to=' + this.to)
                        .then(response => response.json())
                        .then(json => {
                            return json.amount
                        });
            }
        }
}
</script>
