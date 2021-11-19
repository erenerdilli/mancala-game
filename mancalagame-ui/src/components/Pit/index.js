import React from 'react'
import Stone from '../Stone'
import useStyles from './styles'

const Pit = ({ stoneCount, color, isPool, onClick }) => {
  const classes = useStyles({ isPool })

  return (
    <div className={classes.root} onClick={onClick}>
      {[...Array(stoneCount)].map((value, index) => (
        <div key={index} style={{ margin: 5 }}>
          <Stone color={color} />
        </div>
      ))}
    </div>
  )
}

export default Pit
