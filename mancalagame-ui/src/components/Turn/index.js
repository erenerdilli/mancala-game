import React from 'react'
import useStyles from './styles'

const Turn = ({ playerId }) => {
  const classes = useStyles({ playerId })

  return (
    <div className={classes.turnWrapper}>
      Turn: <span className={classes.playerName}>Player {playerId + 1}</span>
    </div>
  )
}

export default Turn
