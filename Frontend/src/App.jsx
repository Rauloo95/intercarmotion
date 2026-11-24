import { useState } from 'react'
import './App.css'
import { python_api, java_api } from './Services/Api.js'

function App() {
  const [count, setCount] = useState(0)
  console.log(python_api)
  console.log(java_api)

  return (
    <div className="portal_1">
      <h1>Mi portal gataso</h1>
      <h2 className='java'>PYTHON API = {python_api}</h2>
      <h2 className='python'>JAVA API = {java_api}</h2>

    </div>


  )
}

export default App
