//
//  DetailsConjugation.swift
//  iosApp
//

import SwiftUI
import shared

struct DetailsConjugation: View {
    
    let model: DetailsViewModel.Conjugation
    
    var body: some View {
        
        HStack {
            
            VStack(alignment: .leading, spacing: 16) {
                
                Text("Conjugations (Formal)")
                    .font(.title)
                    .fontWeight(.semibold)
                    .foregroundColor(Color.Neomorph.text)
                
                VStack(alignment: .leading, spacing: 12) {
                    
                    Row(title: "Long", value: model.presentFormal)
                    
                    Row(title: "Negative", value: model.presentFormalNegative)
                    
                    Row(title: "Past", value: model.pastFormal)
                    
                    Row(title: "Negative Past", value: model.pastFormalNegative)
                }
                
                Text("Conjugations (Informal)")
                    .font(.title)
                    .fontWeight(.semibold)
                    .foregroundColor(Color.Neomorph.text)
                
                VStack(alignment: .leading, spacing: 12) {
                    
                    Row(title: "Long", value: model.presentInformal)
                    
                    Row(title: "Negative", value: model.presentInformalNegative)
                    
                    Row(title: "Past", value: model.pastInformal)
                    
                    Row(title: "Negative Past", value: model.pastInformalNegative)
                }
                
                Text("Conjugations (others)")
                    .font(.title)
                    .fontWeight(.semibold)
                    .foregroundColor(Color.Neomorph.text)
                
                VStack(alignment: .leading, spacing: 12) {
                    
                    Row(title: "Te", value: model.te)
                    
                    if model.showImperative {
                        Row(title: "Imperative", value: model.imperative)
                    }
                    
                    if model.showVolitional {
                        Row(title: "Volitional", value: model.volitional)
                    }
                    
                    if model.showHypothetical {
                        Row(title: "Hypothetical", value: model.hypothetical)
                    }
                    
                    if model.showPotential {
                        Row(title: "Potential", value: model.potential)
                    }
                }
            }
            
            Spacer()
        }
    }
}

fileprivate struct Row: View {
    
    let title: String
    let value: String
    
    var body: some View {
        GeometryReader { geometry in
            HStack {
                HStack {
                    Text(self.title)
                        .font(.body)
                        .fontWeight(.semibold)
                        .foregroundColor(Color.Neomorph.text)
                        .opacity(0.6)
                    
                    Spacer()
                }
                .frame(width: geometry.size.width * 0.4)
                
                HStack {
                    Text(self.value)
                        .font(.body)
                        .fontWeight(.semibold)
                        .foregroundColor(Color.Neomorph.text)
                    
                    Spacer()
                }
                .frame(width: geometry.size.width * 0.6)
            }
        }
        .frame(height: 24)
    }
}

struct DetailsConjugationPreview: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)

            DetailsConjugation(model: DetailsViewModel.Conjugation(presentFormal: "怒ります", presentFormalNegative: "怒りません", pastFormal: "怒りました", pastFormalNegative: "怒りませんでした", presentInformal: "怒る", presentInformalNegative: "怒らない", pastInformal: "怒った", pastInformalNegative: "怒りなかった", te: "怒って"
            ))
            .padding()
        }
    }
}
