import OpenAI from "openai";
import * as dotenv from "dotenv";

dotenv.config(); // Carga variables de entorno desde .env

const token = process.env.GITHUB_TOKEN;
const endpoint = "https://models.github.ai/inference";
const model = "openai/gpt-4.1";

async function main() {
    const client = new OpenAI({
        baseURL: endpoint,
        apiKey: token
    });

    const response = await client.chat.completions.create({
        messages: [
            { role: "system", content: "You are a helpful assistant." },
            { role: "user", content: "que es ser mapero?" }
        ],
        temperature: 1.0,
        top_p: 1.0,
        model: model
    });

    console.log(response.choices[0].message.content);
}

main().catch((err) => {
    console.error("Error:", err);
});