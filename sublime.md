Sublime scripts
---------------

Replace java toString to json

    import re; import json; sel = view.sel()[0]; view.replace(view.begin_edit(), sel, json.dumps(json.loads(re.sub(r':([A-Za-z0-9-]+)', r':"\1"', re.sub(r'([A-Za-z]+):', r'"\1":', re.sub('[A-Za-z]+\{', '{', re.sub('\'', '"', re.sub('=', ':', view.substr(sel))))))), indent=4))
