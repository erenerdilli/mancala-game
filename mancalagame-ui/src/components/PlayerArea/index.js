import React from 'react'
import Pit from '../Pit'
import { COLORS, POOL_INDEX } from 'constants.js'
import useStyles from './styles'

const PlayerArea = ({ id, pits, onPitClick }) => {
  const classes = useStyles({ id })

  return (
    <div>
      <h2>Player {id + 1}</h2>
      <div className={classes.pitArea}>
        {pits.map((stoneCount, index) => (
          <Pit
            key={index}
            stoneCount={stoneCount}
            color={COLORS[id]}
            isPool={index === POOL_INDEX}
            onClick={() => onPitClick({ playerId: id, pitId: index })}
          />
        ))}
      </div>
    </div>
  )
}

export default PlayerArea
