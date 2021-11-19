import React, { useState, useEffect } from 'react'
import { PlayerArea, Turn } from 'components'
import { init as initApi, playTurn as playTurnApi } from 'api/game'
import { POOL_INDEX } from 'constants.js'
import useStyles from './styles'

const Game = () => {
  const classes = useStyles()

  const [gameState, setGameState] = useState(undefined)
  const [error, setError] = useState(undefined)

  const initGame = async () => {
    try {
      const response = await initApi()
      setGameState(response.data)
    } catch (err) {
      setError(err)
    }
  }

  useEffect(() => {
    initGame()
  }, [])

  if (!gameState) {
    return null
  }
  if (error) {
    return <div>{error.message}</div>
  }

  const { turn, players, winnerIndex, endOfGame } = gameState

  if (endOfGame === true) {
    return <div>End of game. Winner: Player{winnerIndex+1}</div>
  }

  const handlePitClick = async ({ playerId, pitId }) => {
    if (pitId === POOL_INDEX) {
      return alert('Pool cannot be chosen!')
    }
    if (playerId === turn) {
      try {
        const response = await playTurnApi({ playerId, pitId })
        setGameState(response.data)
      } catch (err) {
        setError(err)
      }
    } else {
      alert('It is not your turn!')
    }
  }

  return (
    <div className={classes.root}>
      {players.map((player) => {
        const { id, pits } = player
        return (
          <PlayerArea
            key={id}
            id={id}
            pits={pits}
            onPitClick={handlePitClick}
          />
        )
      })}
      <Turn playerId={turn} />
    </div>
  )
}

export default Game
