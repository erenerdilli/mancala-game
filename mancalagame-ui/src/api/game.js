import axios from 'utils/axios'

export const init = async () => {
  return axios.get('http://localhost:8080/api/v1/init')
}

export const playTurn = async ({ playerId, pitId }) => {
  return axios.get('http://localhost:8080/api/v1/playturn', {
    params: { playerId, pitId }
  })
}