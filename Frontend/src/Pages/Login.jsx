import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../Context/AuthContext.jsx";
import { loginRequest } from "../Services/AuthServices.js";





function Login() {
    //estados y hooks
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);
    const { login } = useAuth();
    const navigate = useNavigate()

    async function handleSubmit(e) {
        e.preventDefault();
        setError("");
        setLoading(true);

        try {

            const data = await loginRequest(email, password);
            login(data.token, { email })

            //navegamos a favoritos de momento
            navigate("/favourites");




        } catch (error) {

            setError(error.message)
        }

        finally {
            setLoading(false);
        }


    }

    return (
        <div>

            <h1>Login v1</h1>

            <form onSubmit={handleSubmit} className="flex flex-col gap-4 max-w-sm mx-auto mt-6">
                <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Email"
                    required
                    className="border border-gray-400 rounded px-3 py-2 bg-white text-black"
                />
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Contraseña"
                    required
                    className="border border-gray-400 rounded px-3 py-2 bg-white text-black"
                />

                {error && <p className="text-red-500 text-sm">{error}</p>}

                <button
                    type="submit"
                    disabled={loading}
                    className="bg-blue-600 text-white py-2 rounded hover:bg-blue-700 disabled:opacity-50"
                >
                    {loading ? "Iniciando sesión..." : "Iniciar sesión"}
                </button>
            </form>
        </div>
    )
}
export default Login
