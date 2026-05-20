from flask import Flask, request, jsonify
from flask_cors import CORS
import re
import PyPDF2

app = Flask(__name__)
CORS(app)

SKILL_DB = [
    "python", "java", "sql", "excel",
    "machine learning", "deep learning",
    "data analysis", "power bi",
    "communication", "problem solving",
    "dsa", "spring", "flask", "analytics"
]

ROLE_SKILLS = {
    "software developer": ["python", "java", "dsa", "sql", "spring"],
    "data analyst": ["python", "sql", "excel", "power bi", "data analysis"],
    "business analyst": ["excel", "communication", "sql", "analytics"]
}

def clean_text(text):
    text = text.lower()
    text = re.sub(r"[^a-z\s]", " ", text)
    return text

def extract_skills(text):
    text = clean_text(text)
    return [skill for skill in SKILL_DB if skill in text]

def calculate_score(skills, experience, projects, role):
    role = role.lower()

    required_skills = ROLE_SKILLS.get(role, [])

    match = 0
    for skill in required_skills:
        if skill in skills:
            match += 1

    skill_score = (match / len(required_skills)) * 60 if required_skills else 0
    exp_score = min(experience * 5, 25)
    proj_score = min(projects * 3, 15)

    return round(min(skill_score + exp_score + proj_score, 100))

def extract_text_from_pdf(file):
    reader = PyPDF2.PdfReader(file)
    text = ""
    for page in reader.pages:
        text += page.extract_text() or ""
    return text

@app.route("/analyze", methods=["POST"])
def analyze():
    data = request.json

    resume_text = data.get("resume_text", "")
    experience = int(data.get("experience", 0))
    projects = int(data.get("projects", 0))
    role = data.get("role", "")

    skills = extract_skills(resume_text)
    score = calculate_score(skills, experience, projects, role)

    return jsonify({
        "skills_found": skills,
        "score": score
    })

@app.route("/analyze-pdf", methods=["POST"])
def analyze_pdf():

    file = request.files["file"]
    role = request.form.get("role", "")
    experience = int(request.form.get("experience", 0))
    projects = int(request.form.get("projects", 0))

    text = extract_text_from_pdf(file)
    skills = extract_skills(text)
    score = calculate_score(skills, experience, projects, role)

    return jsonify({
        "skills_found": skills,
        "score": score
    })

if __name__ == "__main__":
    app.run(port=5000, debug=True)