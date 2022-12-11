//
//  DetailsSentence.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import shared
import SentenceFeature

struct DetailsSentence: View {
    
    let model: DetailsViewModel.Sentences
    let word: String
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("Example sentences")
                .font(.title)
                .fontWeight(.semibold)
                .padding(.bottom, 8)
            
            VStack(spacing: 16) {
                ForEach(self.model.items, id: \.self) { item in
                    
                    NavigationLink {
                        SentenceDetailsScreen(id: item.id)
                    } label: {
                        Row(japanese: item.japanese, english: item.english)
                            .padding()
                    }
                    .navigationBarHidden(true)
                }
                
                if self.model.showMore {
                    NavigationLink {
                        SentenceListScreen(word: self.word)
                    } label: {
                        ShowMore()
                            .padding()
                    }
                    .navigationBarHidden(true)
                }
            }
        }
    }
}

fileprivate struct Row: View {
    
    let japanese: String
    let english: String
    
    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 4) {
                Text(self.japanese)
                    .font(.body)
                    .fontWeight(.semibold)
                    .multilineTextAlignment(.leading)
                
                Text(self.english)
                    .font(.body)
                    .multilineTextAlignment(.leading)
                    .opacity(0.6)
            }
            
            Spacer()
        }
    }
}

private struct ShowMore: View {
    
    var body: some View {
        HStack {
            Text("See all sentences")
                .font(.system(size: 14))
                .fontWeight(.semibold)
            
            Spacer()
        }
    }
}

struct DetailsSentencePreview: PreviewProvider {
    
    private static let model = DetailsViewModel.Sentences(items: [
        SentenceResult(
            id: 1,
            japanese: "私の時計はきちんと動いている。",
            english: "My watch is running all right."
        ),
        SentenceResult(
            id: 2,
            japanese: "自分の部屋は出来るだけきちんとしておきたい。",
            english: "I want to keep my room as neat as possible."
        )
    ], sentenceCount: 10)
    
    static var previews: some View {
        ZStack {
            DetailsSentence(model: self.model, word: "部屋")
                .padding()
        }
    }
}
